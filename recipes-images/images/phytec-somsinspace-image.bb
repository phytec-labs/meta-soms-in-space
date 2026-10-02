require recipes-images/images/phytec-headless-image.bb

SUMMARY = "This image is designed to show development of a GoDot demo \
           running as an application on wayland."

IMAGE_FEATURES += "\
    ssh-server-openssh \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', '', d)} \
"

LICENSE = "MIT"

IMAGE_INSTALL += "\
    packagegroup-base \
    packagegroup-gstreamer \
    ${@bb.utils.contains("DISTRO_FEATURES", "virtualization", "packagegroup-virtualization", "", d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston weston-init', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland', '', d)} \
    soms-in-space \
"

# Install the Vulkan loader and TI's PowerVR Rogue Vulkan driver so Godot can
# use its Vulkan renderer. Only TI's vendor GPU stack provides libvk-rogue.
IMAGE_INSTALL += "${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', \
    bb.utils.contains('DISTRO_FEATURES', 'vulkan wayland', 'vulkan-loader libvk-rogue', '', d), '', d)}"
