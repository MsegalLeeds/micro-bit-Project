# Readings

The starter uses one line per reading, with three comma-separated fields and no header:

```text
metric,value,unit
```

Use letters, digits or underscores for the metric and unit. Use a signed or unsigned whole number or decimal for the value, for example `temperature,-2.5,C`. Give a count the unit `count` or a specific unit such as `presses`.

[Sample readings](sample.csv) come from two button presses after a reset. The dashboard loads this file on startup. To test a different device, paste its serial output into the dashboard. Agree changes to the fields with both teams before updating producers and consumers.
