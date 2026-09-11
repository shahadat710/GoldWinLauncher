# GoldWin Launcher — APK বানানোর ধাপ (কোনো কোডিং লাগবে না)

## যা এই প্রজেক্ট করে
- হোম স্ক্রিনে শুধু ২টা বাটন: **GoldWin** এবং **Settings (Wi-Fi)**
- Kiosk Mode / Lock Task Mode ব্যবহার করে না
- তাই প্রিন্টার (Print Spooler) স্বাভাবিকভাবে কাজ করবে
- Back বাটনে GoldWin থেকে স্বাভাবিকভাবে এই হোম স্ক্রিনে ফিরে আসা যাবে

## ধাপ ১ — প্যাকেজ নাম চেক করুন (গুরুত্বপূর্ণ)
`app/src/main/java/com/goldwin/launcher/MainActivity.java` ফাইলে
`TARGET_PACKAGE` ভ্যারিয়েবলে যে নাম আছে
(`org.chromium.webapk.a99635ed460bff022_v2`), সেটা আপনার ডিভাইসের
GoldWin অ্যাপের সাথে মিলিয়ে নিন:
Settings → Apps → GoldWin → App info (Package name)
`app/src/main/AndroidManifest.xml`-এর `<queries>` অংশেও একই নাম আছে,
দরকার হলে দুই জায়গাতেই বদলান।

## ধাপ ২ — GitHub-এ আপলোড করুন (কোনো কমান্ড লাগবে না)
1. https://github.com -এ লগইন/সাইনআপ করুন (ফ্রি)
2. **New repository** বাটনে ক্লিক করুন, নাম দিন (যেমন: `goldwin-launcher`),
   Public/Private যেকোনোটা রাখতে পারেন, **Create repository**
3. পরের পেজে **"uploading an existing file"** লিংকে ক্লিক করুন
4. এই পুরো ফোল্ডারের সব ফাইল/সাব-ফোল্ডার টেনে এনে (drag & drop) দিন
   (`.github` ফোল্ডারসহ সবকিছু)
5. নিচে **Commit changes** বাটনে ক্লিক করুন

## ধাপ ৩ — বিল্ড হওয়ার জন্য অপেক্ষা করুন
1. রিপোজিটরির উপরের **Actions** ট্যাবে যান
2. "Build APK" নামে একটা রান দেখাবে, সেটাতে ক্লিক করুন
3. ২-৫ মিনিট অপেক্ষা করুন — সবুজ ✅ চিহ্ন এলে বিল্ড সফল হয়েছে

## ধাপ ৪ — APK ডাউনলোড করুন
1. সেই রান পেজের নিচে **Artifacts** সেকশনে
   **GoldWinLauncher-apk** নামে একটা জিপ ফাইল পাবেন
2. সেটা ডাউনলোড করুন, আনজিপ করলে `app-debug.apk` পাবেন

## ধাপ ৫ — ডিভাইসে ইনস্টল করুন
1. `app-debug.apk` ফাইলটা M1K ডিভাইসে পাঠান (USB / Google Drive / Email)
2. ফাইল ম্যানেজার দিয়ে খুলে ইনস্টল করুন
   (প্রথমবার "Install unknown apps" অনুমতি চাইতে পারে — Allow করুন)
3. Settings → Apps → Default apps → Home app →
   **GoldWin Launcher** সিলেক্ট করুন
4. Home বাটন চাপলে এখন থেকে শুধু GoldWin + Settings বাটন দেখাবে

## সমস্যা হলে
- GoldWin বাটনে চাপলে "app not found" দেখালে → ধাপ ১-এ প্যাকেজ নাম ভুল আছে,
  ঠিক করে আবার আপলোড করলে Actions আবার নতুন APK বানাবে
- প্রিন্ট সমস্যা হলে এই লঞ্চারের কোনো সম্পর্ক নেই এই ব্যাপারে,
  কারণ এটা কোনো সিস্টেম সার্ভিস ব্লক করে না
