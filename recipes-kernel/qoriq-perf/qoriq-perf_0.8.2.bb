DESCRIPTION = "QorIQ extension to Perf for supporting non core counters"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=e29234dd5d40dc352cc60cc0c93437ba"

DEPENDS += "virtual/kernel"

SRC_URI = "git://git.freescale.com/ppc/sdk/qoriq-perf.git"
SRCREV = "7beb3783edac66bab00c85d99a7b073f569af7fd"

COMPATIBLE_MACHINE = "(b4860qds)"

S = "${WORKDIR}/git"

PROCESSOR_REV = "B4860_R1"

inherit module autotools

EXTRA_OECONF += "--with-linux=${STAGING_KERNEL_DIR} \
                 --with-processor=${PROCESSOR_REV}"

EXTRA_OEMAKE += 'SYSROOT="${D}"'


python () {
        ma = d.getVar("DISTRO_FEATURES", True)
        arch = d.getVar("OVERRIDES", True)

        # the : after the arch is to skip the message on 64b
        if not "multiarch" in ma and ("e5500:" in arch or "e6500:" in arch):
                raise bb.parse.SkipPackage("Building the kernel for this arch requires multiarch to be in DISTRO_FEATURES")

        promote_kernel = d.getVar('BUILD_64BIT_KERNEL')

        if promote_kernel == "1":
                d.setVar('KERNEL_CC_append', ' -m64')
                d.setVar('KERNEL_LD_append', ' -melf64ppc')

        error_qa = d.getVar('ERROR_QA', True)
        if 'arch' in error_qa:
                d.setVar('ERROR_QA', error_qa.replace(' arch', ''))
}

