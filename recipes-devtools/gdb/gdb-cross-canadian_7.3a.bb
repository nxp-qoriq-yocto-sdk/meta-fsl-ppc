require recipes-devtools/gdb/gdb-common.inc
require recipes-devtools/gdb/gdb-cross-canadian.inc
require gdb-7.3a.inc

PR = "${INC_PR}.2"

GDBPROPREFIX = "--program-prefix='${TARGET_PREFIX}'"
EXPAT = "--with-expat"

S = "${WORKDIR}/${BPN}-7.3"
