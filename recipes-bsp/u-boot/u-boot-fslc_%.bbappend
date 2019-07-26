FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

UPDATEHUB_IMX_PATCHES = " \
    file://0001-UpdateHub-Add-common-header.patch \
    file://0002-ARM-mx6sabre_common-Add-support-to-UpdateHub.patch \
    file://0003-ARM-wandboard-Add-support-to-UpdateHub.patch \
    file://0004-ARM-warp7-Add-support-to-UpdateHub.patch \
    file://0005-ARM-pico-imx7d-Add-support-to-UpdateHub.patch \
    file://0006-ARM-colibri_imx6-Set-bootcmd-command-to-load-bootscr.patch \
"

SRC_URI_append_updatehub-imx = " ${UPDATEHUB_IMX_PATCHES}"
