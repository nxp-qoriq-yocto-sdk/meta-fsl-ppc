SUMMARY = "Display or change ethernet card settings"
DESCRIPTION = "A small utility for examining and tuning the settings of your ethernet-based network interfaces."
HOMEPAGE = "http://www.kernel.org/pub/software/network/ethtool/"
SECTION = "console/network"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PR = "r1"

FILESEXTRAPATHS_prepend := "${THISDIR}/ethtool-3.0:"

SRC_URI = "${KERNELORG_MIRROR}/software/network/ethtool/ethtool-${PV}.tar.gz \
    file://ethtool-3.0-Enable-nfc-rx-flow-hashing-on-SPI.patch "
SRC_URI[md5sum] = "a339cbdcbe1c1fbe377c36d84231bda6"
SRC_URI[sha256sum] = "9b9caffc0ee3567b607618ca5bc85505230d66cd9d5caa1c333760b3885f0ae4"

inherit autotools
