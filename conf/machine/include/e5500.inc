TARGET_FPU = "hard"

require conf/machine/include/tune-ppce5500.inc
require conf/machine/include/qoriq-base.inc

MACHINEOVERRIDES .= ":e5500"
