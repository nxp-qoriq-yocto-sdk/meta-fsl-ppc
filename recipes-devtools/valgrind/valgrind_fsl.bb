DESCRIPTION = "Valgrind memory debugger"
HOMEPAGE = "http://valgrind.org/"
LICENSE = "GPLv2 & GPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c46082167a314d785d012a244748d803"

PV = "3.8.1+fsl"

SRC_URI = "http://www.valgrind.org/downloads/valgrind-3.8.1.tar.bz2 \
    file://0001-Baseline.patch \
    file://0002-Configure-for-SPE-on-an-SPE-machine.patch \
    file://0003-Declare-the-64-bit-SPE-GPR-s-to-the-PPC32-guest-stat.patch \
    file://0004-Power-ISA-V2.06B-V2-Sec.-1.6.24-EVX-FORM-extractors.patch \
    file://0005-Extend-addr_align-for-double-word-alignment.patch \
    file://0006-Implement-SPE-Instructions-evldd-evstdd-evxor-base-f.patch \
    file://0007-Implement-SPE-Instructions-evlddx-evstddx.patch \
    file://0008-Implement-SPE-Instructions-evldh-evstdh.patch \
    file://0009-Create-data-pools-per-__ev64_-__-integer-type-and-an.patch \
    file://0010-Implement-SPE-Instructions-evor-evmr.patch \
    file://0011-Implement-SPE-Instructions-evldhx-evstdhx-evldw-evst.patch \
    file://0012-Re-Implement-SPE-Instructions-evldd-evstdd-evlddx-ev.patch \
    file://0013-Remove-some-of-the-verbosity-in-the-test-reporting.patch \
    file://0014-Create-a-data-pool-of-word-values-and-macros-for-get.patch \
    file://0015-Implement-SPE-Instructions-evlwhe-evstwhe-evlwhex-ev.patch \
    file://0016-Implement-SPE-Instructions-evlwhos-evlwhosx-evlwhou-.patch \
    file://0017-Cleanup-evstdd_asm.patch \
    file://0018-Implement-SPE-Instructions-evstwwe-evstwwex-evstwwo-.patch \
    file://0019-Implement-SPE-Instructions-evlhhesplat-evlhhesplatx-.patch \
    file://0020-Implement-SPE-Instructions-evlwhsplat-evlwhsplatx-ev.patch \
    file://0021-Implement-SPE-Instructions-evmergehi-evmergehilo-evm.patch \
    file://0022-Implement-SPE-Instructions-evand-evandc-eveqv-evnand.patch \
    file://0023-Implement-SPE-Instructions-evrlw-evrlwi-evslw-evslwi.patch \
    file://0024-Tests-based-on-SPE-Programming-Interface-Manual-Chap.patch \
    file://0025-Tests-based-on-SPE-Programming-Interface-Manual-Chap.patch \
    file://0026-Tests-based-on-SPE-Programming-Interface-Manual-Chap.patch \
    file://0027-Tests-based-on-SPE-Programming-Interface-Manual-Chap.patch \
    file://0028-Implement-SPE-Instructions-evextsb-evextsh.patch \
    file://0029-Implement-SPE-Instructions-evcntlsw-evcntlzw.patch \
    file://0030-Implement-SPE-Instructions-evsplatfi-evsplati.patch \
    file://0031-Implement-SPE-Instructions-evneg-evabs-evrndw.patch \
    file://0032-Implement-SPE-Instructions-evcmpeq-evcmpgts-evcmpgtu.patch \
    file://0033-Power-ISA-V2.06B-V2-Sec.-1.6.25-EVS-FORM-extractors.patch \
    file://0034-Implement-SPE-Instructions-evsel.patch \
    file://0035-Provide-for-being-able-to-iterate-the-regression-tes.patch \
    file://0036-Implement-SPE-Instructions-evmra-evaddsmiaaw-evaddum.patch \
    file://0037-Implement-SPE-Instructions-evaddiw-evaddw-evsubifw-e.patch \
    file://0038-Implement-SPE-Instructions.patch \
    file://0039-Implement-SPE-Instructions-evmwsmfan-evmwsmfaa-evmws.patch \
    file://0040-Implement-SPE-Instructions.patch \
    file://0041-Add-document-FSL-SPE-README.txt.patch \
    file://0042-Mark-version-as-3.8.1-FSL-Thu-Mar-28-165508-PDT-2013.patch \
    file://0043-Minor-edits-to-FSL-SPE-README.txt.patch \
    file://0044-Minor-edits-FSL-SPE-README.txt.patch \
    file://0045-VEX-priv-guest_ppc_helpers.c-Use-__ev_convert_s64-in.patch \
    file://0046-Implement-SPE-Instructions-evmhegsmfaa-evmhegsmfan-e.patch \
    file://0047-Implement-SPE-Instructions-evmhegsmiaa-evmhegsmian-e.patch \
    file://0048-Fix-spelling-error-in-FSL-SPE-README.txt.patch \
    file://0049-Mark-version-as-3.8.1-FSL-Mon-Apr-8-110008-PDT-2013.patch \
    file://0050-Adding-new-file-regtest-power7-64.default-build.log.patch \
    file://0051-Cleanups-in-memcheck-tests-ppc32-test_spe.c.patch \
    file://0052-Implement-Instructions-mfspefscr-mtspefscr.patch \
    file://0053-Implement-SPE-Instructions-efdcmpeq-efdabs-efdadd-ef.patch \
    file://0054-Implement-SPE-Instructions-efdcmpgt-efdcmplt-efdctsf.patch \
    file://0055-Implement-SPE-Instructions.patch \
    file://0056-Implement-SPE-Instructions.patch \
    file://0057-Implement-SPE-Instructions.patch \
    file://0058-Implement-SPE-instructions-evaddssiaaw-evaddusiaaw-e.patch \
    file://0059-Cleanup-dirty-helper-parts.patch \
    file://0060-Run-memcheck-tests-ppc32-test_spe-only-on-Power-SPE-.patch \
    file://0061-Test-that-Valgrind-will-determine-an-out-of-bounds-a.patch \
    file://0062-Mark-version-as-3.8.1-FSL-SDK-1.4-spe-Fri-May-24-080.patch \
    file://0063-FSL-SPE-README.txt-Add-prlimit-2-being-an-unsupporte.patch \
    file://0064-Add-system-tests-based-on-the-FSL-SPE-README.txt-s-s.patch \
    file://0065-misc-updates-for-sdk-1.6.patch \
    file://valgrind-3.8.1-sepbuildfix.patch \
    file://configure-with-newer-glibc.patch \
    file://fix-out-of-tree-builds-with-newer-glibc.patch \
    file://eglibc-2.18.patch \
    file://eglibc-2.19.patch \
    file://valgrind-3.8.1-Bug-308573.patch \
"
SRC_URI[md5sum] = "288758010b271119a0ffc0183f1d6e38"
SRC_URI[sha256sum] = "473be00576bed311a662b277a2bfbe97d9cca4058e68619a0e420c9fc19958db"

inherit autotools

S = "${WORKDIR}/${BPN}-3.8.1"

EXTRA_OECONF = "--without-mpicc"
EXTRA_OEMAKE = "-w"
PARALLEL_MAKE = ""

do_install_append () {
    install -m 644 ${B}/default.supp ${D}/${libdir}/valgrind/
    find ${D} -type f | xargs sed -i '1s,#!.*perl,#!${USRBINPATH}/env perl,'
}

RDEPENDS_${PN} += "perl"

FILES_${PN}-dbg += "${libdir}/${PN}/*/.debug/*"
RRECOMMENDS_${PN}_powerpc += "${TCLIBC}-dbg"
RRECOMMENDS_${PN}_powerpc64 += "${TCLIBC}-dbg"
RDEPENDS_${PN} += "perl"
