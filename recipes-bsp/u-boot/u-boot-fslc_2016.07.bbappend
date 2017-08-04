FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

UPDATEHUB_IMX_PATCHES = " \
    file://0001-UpdateHub-Add-common-header.patch \
    file://0002-mx6sabre_common-Add-support-to-UpdateHub.patch \
    file://0003-wandboard-Add-support-to-UpdateHub.patch \
"

SRC_URI_append_updatehub-imx = " ${UPDATEHUB_IMX_PATCHES}"
