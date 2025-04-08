# meta-soms-in-space

A Yocto layer for deploying the "Soms In Space" game on PHYTEC hardware.

## Description

This layer provides recipes and configuration to install the Soms In Space game, a PHYTEC demo project where you play as a PHYTEC SoM making your way to outer space. It configures the game to run automatically as a systemd service and includes display configuration options.

## Dependencies

* poky (zeus or later)
* meta-phytec (for PHYTEC hardware support)
  * meta-ampliphy

## Configuration

The layer provides the following display configuration options:

* `DISPLAY_TYPE`: Configure display output
  * `lvds`: Use LVDS display (default)
  * `hdmi`: Use HDMI display
  * `none`: Don't configure display

* `DISPLAY_ROTATION`: Configure screen rotation
  * `rotate-90`: Rotate 90 degrees (default)
  * `normal` or `none`: No rotation
  * `rotate-180`: Rotate 180 degrees
  * `rotate-270`: Rotate 270 degrees

## Usage

1. Add this layer to your Yocto build
2. Add `soms-in-space` to your image
3. Configure display options in your local.conf (optional)

Example local.conf configuration:

``` ini
# Default settings. This creates a portrait display using LVDS.
DISPLAY_TYPE = "lvds"
DISPLAY_ROTATION = "rotate-90"

# Switch to HDMI output
DISPLAY_TYPE = "hdmi"
# Disable rotation
DISPLAY_ROTATION = "normal"
```

## License

This layer is provided under the MIT license.
