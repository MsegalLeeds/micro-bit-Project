from microbit import button_a, display, sleep

# Count button A presses since the board started.
presses = 0
while True:
    if button_a.was_pressed():
        presses += 1
        display.show(str(presses % 10))
        print("button_a,%d,presses" % presses)
    sleep(20)
