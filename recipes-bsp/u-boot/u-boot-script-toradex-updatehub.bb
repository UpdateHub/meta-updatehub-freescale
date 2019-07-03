LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "file://bootscript.txt"

inherit deploy

do_compile[noexec] = "1"

do_mkimage () {
    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${WORKDIR}/bootscript.txt \
                  ${B}/bootscript
}

addtask mkimage after do_compile before do_install

do_install () {
    install -Dm 0644 ${B}/bootscript ${D}/bootscript
}

do_deploy () {
    install -Dm 0644 ${B}/bootscript ${DEPLOYDIR}/bootscript-${MACHINE}-${PV}-${PR}
    cd ${DEPLOYDIR}
    rm -f bootscript-${MACHINE}
    ln -sf bootscript-${MACHINE}-${PV}-${PR} bootscript-${MACHINE}
}

addtask deploy after do_install before do_build

FILES_${PN} += "/"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(apalis-imx6|colibri-imx6)"
