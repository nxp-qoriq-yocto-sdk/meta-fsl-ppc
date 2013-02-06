SUMMARY = "System-Wide Profiler"
DESCRIPTION = "OProfile is a system-wide profiler for Linux systems, capable \
of profiling all running code at low overhead."
HOMEPAGE = "http://oprofile.sourceforge.net/news/"
BUGTRACKER = "http://sourceforge.net/tracker/?group_id=16191&atid=116191"

LICENSE = "LGPLv2.1+ & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://libopagent/opagent.h;beginline=5;endline=26;md5=4f16f72c7a493d8a4704aa18d03d15c6 \
                    file://daemon/liblegacy/p_module.h;beginline=2;endline=20;md5=fc23a43455edf185307274a99730b6e4"

SECTION = "devel"

PR = "r1"

DEPENDS = "popt binutils"
RDEPENDS_${PN} = "binutils-symlinks"
RRECOMMENDS_${PN} = "kernel-vmlinux"

FILES_${PN} = "${bindir} ${libdir}/${BPN}/lib*.so.* ${datadir}/${BPN}"
FILES_${PN}-dev += "${libdir}/${BPN}/lib*.so ${libdir}/${BPN}/lib*.la"
FILES_${PN}-staticdev += "${libdir}/${BPN}/lib*.a"

SRC_URI = "${SOURCEFORGE_MIRROR}/oprofile/oprofile-${PV}.tar.gz \
           file://opstart.patch \
           file://oprofile-no-query-modules.patch \
           file://oprofile-0.9.6-e500mc-support.patch \
           file://oprofile-0.9.6-e500mc-support-2.patch \
           file://oprofile-0.9.6-add-e500mc-support-in-op_events.patch \
           file://oprofile-0.9.6-add-e6500-support.patch"

SRC_URI[md5sum] = "4e407093ac06200185d5a5e6437d7242"
SRC_URI[sha256sum] = "3f0dd40a7749fc650d25d79d42ebbff8f3b6db310c36e7c3839696cc09077880"
S = "${WORKDIR}/oprofile-${PV}"

inherit autotools

EXTRA_OECONF = "--with-kernel-support --without-x"
