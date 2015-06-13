DESCRIPTION = "Freescale TLU test package"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8a71d0475d08eee76d8b6d0c6dbec543"

SRC_URI = "git://git.freescale.com/ppc/sdk/fsl-tlu.git"
SRCREV = "8837cce3c86b30c0931c319e9e1a8ca622ae5354"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${sbindir}/fsl_tlu
    find . -type f -exec cp {} ${D}${sbindir}/fsl_tlu/ \;
}

