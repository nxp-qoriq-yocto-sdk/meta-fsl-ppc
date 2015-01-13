# Provides the QorIQ common settings
require conf/machine/include/soc-family.inc

# providers
PREFERRED_PROVIDER_virtual/kernel ?= "linux-qoriq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-qoriq"

# versions
PREFERRED_VERSION_qemu = "1.7+fsl"
PREFERRED_VERSION_valgrind_e500v2 = "3.8.1+fsl"
PREFERRED_VERSION_ptpd = "2.3.0"

# settings
MACHINE_FEATURES = "keyboard pci ext2 ext3 serial"
MACHINE_EXTRA_RRECOMMENDS += "udev-extraconf kernel-modules"

IMAGE_CLASSES += "image_types_uboot"
EXTRA_IMAGEDEPENDS += "u-boot"

KERNEL_IMAGETYPE ?= "uImage"

MACHINEOVERRIDES .= ":qoriq-ppc"

SERIAL_CONSOLES ?= "115200;ttyS0 115200;ttyS1 115200;ttyEHV0"
SERIAL_CONSOLES_CHECK ?= "${SERIAL_CONSOLES}"

USE_VT = "0"

MKUBIFS_ARGS ??= "-m 512 -e 15360 -c 12000"
UBINIZE_ARGS ??= "-m 512 -p 16KiB -s 512"

