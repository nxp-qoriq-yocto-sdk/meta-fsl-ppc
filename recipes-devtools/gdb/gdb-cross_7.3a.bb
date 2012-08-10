require recipes-devtools/gdb/gdb-cross.inc
require gdb-7.3a.inc

SRC_URI += "file://sim-install-6.6.patch"
EXPAT = "--with-expat"

PR = "${INC_PR}.1"

S = "${WORKDIR}/${BPN}-7.3"
