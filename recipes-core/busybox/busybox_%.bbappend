FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

BUSYBOX_SPLIT_SUID = "0"

SRC_URI_append_qoriq-ppc = " file://defconfig-fsl"

do_configure_prepend_qoriq-ppc () {
        cp ${WORKDIR}/defconfig-fsl ${WORKDIR}/defconfig
}

