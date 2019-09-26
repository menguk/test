TARGETS = console-setup resolvconf mountkernfs.sh ufw apparmor hostname.sh screen-cleanup plymouth-log udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh checkroot.sh lvm2 mountdevsubfs.sh networking urandom iscsid checkfs.sh open-iscsi bootmisc.sh mountnfs.sh kmod procps mountall.sh checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
networking: resolvconf mountkernfs.sh urandom procps
urandom: hwclock.sh
iscsid: networking
checkfs.sh: cryptdisks lvm2 checkroot.sh
open-iscsi: networking iscsid
bootmisc.sh: udev checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh
mountnfs.sh: networking
kmod: checkroot.sh
procps: mountkernfs.sh udev
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
mountall-bootclean.sh: mountall.sh
mountnfs-bootclean.sh: mountnfs.sh
