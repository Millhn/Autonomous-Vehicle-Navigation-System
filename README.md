# 🚗 Otonom Araç Navigasyon Sistemi için Güvenlik Modülü

## 📌 Proje Tanımı

Bu projede, iki farklı engel sınıfını en güvenli şekilde ayıran doğrusal bir model geliştirilmiştir.
Amaç sadece iki sınıfı ayırmak değil, aynı zamanda **en geniş güvenlik koridorunu** sağlayan ayırıcı doğruyu bulmaktır.

Bu yaklaşım, **maximum margin (maksimum marjin)** prensibine dayanmaktadır.

---

## 🎯 Amaç

Verilen iki boyutlu veri noktalarını:

* Doğrusal olarak ayırmak
* Ayırıcı doğrunun en yakın noktalara olan mesafesini maksimum yapmak
* Böylece en güvenli navigasyon sınırını elde etmek

---

## 🧠 Kullanılan Yöntem

Bu projede klasik SVM optimizasyonu yerine, şu yaklaşım kullanılmıştır:

1. Farklı sınıflardan nokta çiftleri seçilir
2. Bu noktaların orta noktası bulunur
3. Bu noktadan geçen dik ayırıcı doğru oluşturulur
4. Tüm veri kümesi üzerinde test edilir
5. Geçerli doğrular için minimum mesafe hesaplanır
6. En büyük margin değerine sahip doğru seçilir

Bu yöntem, maksimum margin mantığını pratik olarak uygular.

---

## 📐 Matematiksel Model

Ayırıcı doğru:

ax + by + c = 0

Bir noktanın doğruya uzaklığı:

|ax + by + c| / √(a² + b²)

Amaç:
Minimum mesafeyi maksimum yapmaktır.

---

## 🏗️ Yazılım Mimarisi

Proje katmanlı mimari kullanılarak geliştirilmiştir:

* **model/** → Veri yapıları
* **solver/** → Algoritma
* **service/** → Veri üretimi
* **util/** → Yardımcı fonksiyonlar
* **Main.java** → Program giriş noktası

Bu yapı sayesinde:

* Kod okunabilirliği artar
* Bakım kolaylaşır
* Sorumluluklar ayrılmış olur

---

## 📦 Sınıf Açıklamaları

### 🔹 Point2D

2 boyutlu koordinatları temsil eder.

### 🔹 LabeledPoint

Bir noktanın hangi sınıfa ait olduğunu tutar (+1 / -1).

### 🔹 Dataset

Tüm veri kümesini yönetir.

### 🔹 SeparatorLine

Ayırıcı doğruyu temsil eder ve:

* tahmin yapar
* mesafe hesaplar

### 🔹 MaximumMarginSeparatorSolver

Algoritmanın çalıştığı ana sınıftır.

---

## ⚙️ Algoritma Karmaşıklığı

* Aday doğru sayısı: O(n²)
* Her doğru için kontrol: O(n)

Toplam:

👉 **O(n³)**

---

## 📊 Çıktı

Program çalıştırıldığında:

* Ayırıcı doğru denklemi
* Minimum margin değeri
* Destek vektörleri
* Tüm noktaların sınıflandırma sonucu

ekranda gösterilir.

---

## 🎯 Destek Vektörleri

Destek vektörleri:

* Ayırıcı doğruya en yakın noktalardır
* Karar sınırını belirlerler
* Birden fazla olabilir

---

## ⚠️ Sınırlamalar

* Sadece lineer ayrılabilir veri için uygundur
* 2 boyutlu veri üzerinde çalışır
* Brute-force yaklaşımı büyük veri setleri için maliyetlidir

---

## 🚀 Geliştirme Önerileri

* Daha büyük veri setleri ile test
* Grafiksel görselleştirme eklenmesi
* Gerçek zamanlı sistemlere entegrasyon
* SVM optimizasyonu ile geliştirme

---

## ▶️ Nasıl Çalıştırılır?

```bash
javac -d out src/Main.java src/model/*.java src/solver/*.java src/service/*.java src/util/*.java
java -cp out Main
```

---

## 📽️ Demo

Proje çıktısı:

* Veri kümesi listelenir
* Ayırıcı doğru bulunur
* Support vector gösterilir
* Tüm noktalar sınıflandırılır

---

## 📌 Not

Bu proje, maksimum margin prensibini temel alan pratik bir yaklaşım sunmaktadır.
Tam bir SVM optimizasyonu yerine, aday doğrular üzerinden arama yapılmıştır.

---
