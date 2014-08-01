FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append_qoriq-ppc = " \
    file://auto-detect-hostname.patch;patchdir=../ \
"

