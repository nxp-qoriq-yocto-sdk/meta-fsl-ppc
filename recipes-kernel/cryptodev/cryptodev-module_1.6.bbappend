require recipes-kernel/cryptodev/cryptodev-fsl.inc

inherit qoriq_build_64bit_kernel

do_install_append_qoriq-ppc () {
    rm -fr ${D}/usr
}

# NOTE: remove this patch and all traces of DISTRO_FEATURE c29x_pkc
# if pkc-host does not need customized cryptodev patches anymore
SRC_URI_append_qoriq-ppc = "${@base_contains('DISTRO_FEATURES', 'c29x_pkc', ' file://0001-don-t-advertise-RSA-keygen.patch', '', d)}"
