FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append_qoriq-ppc = "\ 
    file://72-fsl-dpaa-persistent-networking.rules \
    file://71-fsl-dpaa-persistent-networking.rules \
"
dpaa_rule="71-fsl-dpaa-persistent-networking.rules"
dpaa_rule_e6500="72-fsl-dpaa-persistent-networking.rules"
dpaa_rule_e6500-64b="72-fsl-dpaa-persistent-networking.rules"
dpaa_rule_t1024="72-fsl-dpaa-persistent-networking.rules"
dpaa_rule_t1023="72-fsl-dpaa-persistent-networking.rules"

do_install_append_qoriq-ppc () {
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/${dpaa_rule} ${D}${sysconfdir}/udev/rules.d

    # skip mmc rpmb partitions
    echo "/dev/mmcblk.*rpmb" >>${D}${sysconfdir}/udev/mount.blacklist
    # skip nbd (network block device)
    echo "/dev/nbd*" >>${D}${sysconfdir}/udev/mount.blacklist
}

