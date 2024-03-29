LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "file://boot.scr"

inherit deploy

do_compile[noexec] = "1"

do_mkimage () {
    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${WORKDIR}/boot.scr \
                  ${B}/boot.scr
}

addtask mkimage after do_compile before do_install

do_install () {
    install -Dm 0644 ${B}/boot.scr ${D}/boot.scr
}

do_deploy () {
    install -Dm 0644 ${B}/boot.scr ${DEPLOYDIR}/boot.scr-${MACHINE}-${PV}-${PR}
    cd ${DEPLOYDIR}
    rm -f boot.scr-${MACHINE}
    ln -sf boot.scr-${MACHINE}-${PV}-${PR} boot.scr-${MACHINE}
}

addtask deploy after do_install before do_build

FILES:${PN} += "/"

PROVIDES += "u-boot-default-script"
RPROVIDES:${PN} += "u-boot-default-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(nitrogen6x|nitrogen6x-lite|nitrogen6sx|nitrogen7)"
