FILESEXTRAPATHS_prepend_fsl := "${THISDIR}/files:"

SRC_URI_append_fsl = " file://gettext.fix_testcase.patch"

PR_append_fsl = "+${DISTRO}.0"
