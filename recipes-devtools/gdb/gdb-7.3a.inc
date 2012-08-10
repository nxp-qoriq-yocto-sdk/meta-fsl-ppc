PR_append_fsl = "+${DISTRO}.0"

SRC_URI_append_fsl = " file://gdb-7.4.1-e500mc-disassemble-fix.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI[md5sum] = "9443a370dabd9ca9c41b496b59f05145"
SRC_URI[sha256sum] = "2c2b26eea33ab477ea1f7739a8e511b1e8d89b6f74d87cdd510c6262a3af82d7"
