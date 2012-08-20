FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

AUTO_ETH0 = '${@base_conditional("ETH0_AUTO_DHCP", "0", "0", "1", d)}'

SRC_URI += "file://71-fsl-dpaa-persistent-networking.rules"

do_install_append () {
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -m 0644 ${WORKDIR}/71-fsl-dpaa-persistent-networking.rules ${D}${sysconfdir}/udev/rules.d
    if [ "0" = "${AUTO_ETH0}" ]; then
        sed -i 's,auto eth0,#auto eth0,g' ${D}${sysconfdir}/network/interfaces
    fi
}

PR_append_fsl = "+${DISTRO}.1"
