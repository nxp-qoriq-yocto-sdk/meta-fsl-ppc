DESCRIPTION = "Openstack demo scripts and configure files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "git://git.freescale.com/ppc/sdk/openstack-demo.git;branch=master"
SRCREV = "9a857f8cc02c02d14f699e7716798d7a8da701c0"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${ROOT_HOME}/Icehouse_conf
    cp -a ${S}/conf/* ${D}${ROOT_HOME}/Icehouse_conf
    install -m 0755 ${S}/scripts/openstack_deploy_T4.sh ${D}${ROOT_HOME}
}

FILES_${PN} += "${ROOT_HOME}"
