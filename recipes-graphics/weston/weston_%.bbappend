FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Display configuration options with defaults
# Options for DISPLAY_TYPE: "lvds" (default), "hdmi", "none"
DISPLAY_TYPE ?= "lvds"
# Options for DISPLAY_ROTATION: "rotate-90" (default), "normal", "rotate-180", "rotate-270", "none"
DISPLAY_ROTATION ?= "rotate-90"

do_install:append() {
    # Skip if explicitly set to "none"
    if [ "${DISPLAY_TYPE}" != "none" ]; then
        if [ -e ${D}${sysconfdir}/xdg/weston/weston.ini ]; then
            # Setup display name based on DISPLAY_TYPE
            if [ "${DISPLAY_TYPE}" = "lvds" ]; then
                DISPLAY_NAME="LVDS-1"
            elif [ "${DISPLAY_TYPE}" = "hdmi" ]; then
                DISPLAY_NAME="HDMI-A-1"
            fi

            # Start building the output section
            OUTPUT_SECTION="[output]\nname=${DISPLAY_NAME}\nmode=1920x1080@60.0"

            # Add transform only if rotation is not "none" or "normal"
            if [ "${DISPLAY_ROTATION}" != "none" ] && [ "${DISPLAY_ROTATION}" != "normal" ]; then
                OUTPUT_SECTION="${OUTPUT_SECTION}\ntransform=${DISPLAY_ROTATION}"
            fi

            # Append to weston.ini
            printf "\n${OUTPUT_SECTION}\n" >> ${D}${sysconfdir}/xdg/weston/weston.ini
        fi
    fi
}
