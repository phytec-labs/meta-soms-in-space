SUMMARY = "Soms In Space Game"
DESCRIPTION = "A PHYTEC demo project where you play as a PHYTEC SoM making your way to outer space"
HOMEPAGE = "https://github.com/phytec-labs/SomsInSpace"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/phytec-labs/SomsInSpace/releases/download/v${PV}/Soms-In-Space.arm64 \
           file://soms-in-space.service \
          "
SRC_URI[sha256sum] = "33175667dd9ec0ae9e0b3cc1cea27d2066f0741156302566c4be36db0d1fd827"

S = "${UNPACKDIR}"

inherit systemd

# Make sure we have these dependencies
RDEPENDS:${PN} += "weston systemd"

# Disable QA check for already-stripped binaries
INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    # Create destination directory
    install -d ${D}/root
    install -d ${D}${systemd_system_unitdir}

    # Install game binary
    install -m 0755 ${UNPACKDIR}/Soms-In-Space.arm64 ${D}/root/

    # Install systemd service file
    install -m 0644 ${UNPACKDIR}/soms-in-space.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "/root/Soms-In-Space.arm64"

SYSTEMD_SERVICE:${PN} = "soms-in-space.service"
SYSTEMD_AUTO_ENABLE = "enable"
