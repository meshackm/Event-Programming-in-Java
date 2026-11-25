#!/usr/bin/env bash
# ------------------------------------------------------------
# Compile and run one class from this project WITHOUT Maven.
#
#   ./run.sh App            # short name, com.example is assumed
#   ./run.sh com.example.LayoutDemo
#
# The guide's route is `mvn clean javafx:run`. That works here too,
# but `mvn` is not on the PATH -- NetBeans ships Maven at
# /opt/netbeans-31/java/maven/bin/mvn. This script skips all that:
# it fetches the same JavaFX 21 artifacts from Maven Central into
# ./lib on first run, then uses javac/java directly. JavaFX is not
# part of the JDK, so the jars have to come from somewhere either
# way.
# ------------------------------------------------------------
set -euo pipefail
cd "$(dirname "$0")"

JFX_VERSION=21.0.5
LIB=lib
BUILD=build
MODULES=javafx.controls,javafx.fxml

# Every class in this project that has a main() method.
runnable() {
    grep -l 'static void main' src/main/java/com/example/*.java \
        | sed 's|.*/||; s|\.java$||' | sort
}

usage() {
    echo "usage: $0 <MainClass>" >&2
    echo >&2
    echo "runnable classes in $(basename "$PWD"):" >&2
    runnable | sed 's/^/  /' >&2
}

if [ $# -lt 1 ]; then
    usage
    exit 64
fi

MAIN="$1"
SHORT="${MAIN##*.}"

# Catch a bad name here, with a useful message, rather than letting
# java fail later with a bare ClassNotFoundException. The most common
# slip is asking for another week's class: App and DashboardApp live
# in Week6, the event demos in Week7.
if ! runnable | grep -qx "$SHORT"; then
    echo "$0: no runnable class named '$SHORT' in $(basename "$PWD")" >&2
    echo >&2
    usage
    exit 64
fi

case "$MAIN" in
    *.*) ;;                        # already fully qualified
    *)   MAIN="com.example.$MAIN" ;;
esac

# ---- 1. fetch JavaFX once ----
if [ ! -d "$LIB" ]; then
    echo "Fetching JavaFX $JFX_VERSION into ./$LIB (first run only)..."
    mkdir -p "$LIB"
    for m in base graphics controls fxml; do
        jar="javafx-$m-$JFX_VERSION-linux.jar"
        url="https://repo1.maven.org/maven2/org/openjfx/javafx-$m/$JFX_VERSION/$jar"
        curl -sfL -o "$LIB/javafx-$m-$JFX_VERSION.jar" "$url" \
            || { echo "Download failed: $url" >&2; rm -rf "$LIB"; exit 1; }
    done
fi

# ---- 2. compile ----
rm -rf "$BUILD"
mkdir -p "$BUILD"
javac --module-path "$LIB" --add-modules "$MODULES" -encoding UTF-8 \
      -d "$BUILD" $(find src/main/java -name '*.java')

# ---- 3. run ----
CP="$BUILD"
[ -d src/main/resources ] && CP="$CP:src/main/resources"

exec java --module-path "$LIB" --add-modules "$MODULES" \
          --enable-native-access=javafx.graphics \
          -cp "$CP" "$MAIN"
