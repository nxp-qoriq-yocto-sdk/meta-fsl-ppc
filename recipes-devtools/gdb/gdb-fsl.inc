PR_append_fsl = "+${DISTRO}.0"

SRC_URI_append_fsl = " file://gdb-7.4.1-e500mc-disassemble-fix.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
