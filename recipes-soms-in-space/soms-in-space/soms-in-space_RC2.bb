SUMMARY = "Soms In Space Game"
DESCRIPTION = "A PHYTEC demo project where you play as a PHYTEC SoM making your way to outer space"
HOMEPAGE = "https://github.com/phytec-labs/SomsInSpace"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/phytec-labs/SomsInSpace/releases/download/RC2/Soms-In-Space.arm64 \
           file://soms-in-space.service \
          "
SRC_URI[md5sum] = "0ca3e19927d35f9d1224d87879363f66"
SRC_URI[sha256sum] = "f26eed3dc481403b80825aa2315bd34446aae02b40ab9965fa0fe4ba392ca92b"

S = "${WORKDIR}"

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
    install -m 0755 ${WORKDIR}/Soms-In-Space.arm64 ${D}/root/

    # Install systemd service file
    install -m 0644 ${WORKDIR}/soms-in-space.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "/root/Soms-In-Space.arm64"

SYSTEMD_SERVICE:${PN} = "soms-in-space.service"
SYSTEMD_AUTO_ENABLE = "enable"
