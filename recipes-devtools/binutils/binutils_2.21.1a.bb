require recipes-devtools/binutils/binutils.inc

PR = "r0"

LIC_FILES_CHKSUM="\
    file://src-release;endline=17;md5=4830a9ef968f3b18dd5e9f2c00db2d35\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674\
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6\
    file://gas/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    file://include/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://include/COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://libiberty/COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7\
    file://bfd/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    "

SRC_URI_fsl = "\
		${GNU_MIRROR}/binutils/binutils-${PV}.tar.bz2 \
		file://bin.e5500.patch \
		file://bin.e6500-2.patch \
		file://bin.e500mc_nop.patch \
		file://libtool-2.4-update_fsl.patch \
		file://binutils-2.19.1-ld-sysroot.patch \
		file://libiberty_path_fix.patch \
		file://binutils-poison.patch \
		file://libtool-rpath-fix.patch \
		"

SRC_URI[md5sum] = "bde820eac53fa3a8d8696667418557ad"
SRC_URI[sha256sum] = "cdecfa69f02aa7b05fbcdf678e33137151f361313b2f3e48aba925f64eabf654"

# 2.21.1a has a mismatched dir name within the tarball
S = "${WORKDIR}/binutils-2.21.1"

BBCLASSEXTEND = "native"
