LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "file://6x_bootscript.txt"

inherit deploy

do_compile[noexec] = "1"

do_mkimage () {
    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${WORKDIR}/6x_bootscript.txt \
                  ${B}/6x_bootscript
}

addtask mkimage after do_compile before do_install

do_install () {
    install -Dm 0644 ${B}/6x_bootscript ${D}/6x_bootscript
}

do_deploy () {
    install -Dm 0644 ${B}/6x_bootscript ${DEPLOYDIR}/6x_bootscript-${MACHINE}-${PV}-${PR}
    cd ${DEPLOYDIR}
    rm -f 6x_bootscript-${MACHINE}
    ln -sf 6x_bootscript-${MACHINE}-${PV}-${PR} 6x_bootscript-${MACHINE}
}

addtask deploy after do_install before do_build

FILES_${PN} += "/"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(nitrogen6x|nitrogen6x-lite|nitrogen6sx|nitrogen7)"
