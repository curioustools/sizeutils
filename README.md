# SizeUtils 
A tiny set of libraries to assist in Memory management and other file related tasks

![](app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png)


## SizeUnit

This little library can be used just like [TimeUnit](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html) from java. It has no dependencies on other libs or even the android framework, so feel free to use in your web,kmm or backend project too!

**0) Installation**

```kotlin
 implementation("io.github.curioustools.sizeutils:sizeunit:<latest_version>")
```
latest version is : ![Maven Central](https://img.shields.io/maven-central/v/io.github.curioustools.sizeutils/sizeunit)



**1) Basic usage :** 

```kotlin
SizeUnit.MB(4).toKiloBytes()            //  4000.0 KB
SizeUnit.MB(4).toBytes()                //  4000000 B
SizeUnit.MB(4).toGigaBytes()            //  0.004 GB
SizeUnit.MB(4).toBits()                 //  3.2E7 b 
SizeUnit.MB(4).toTeraBytes()            //  4.0E-6 TB
SizeUnit.MB(4).toPetaBytes()            //  4.0E-9 PB
```

You can also use the inline function which looks more prettier: 

```kotlin

4.mb.toKiloBytes()            //  4000.0 KB
4.mb.toBytes()                //  4000000 B
4.mb.toGigaBytes()            //  0.004 GB
4.mb.toBits()                 //  3.2E7 b 
4.mb.toTeraBytes()            //  4.0E-6 TB
4.mb.toPetaBytes()            //  4.0E-9 PB
```

**2) Base Conversion** : Many memory systems use  SI standard for representing memory , where 1kb = 1024 bytes. however other systems use IEC standard, where  1 kib = 1000 bytes and so forth. So we gave the ability to convert in the hands of the user. default is DECIMAL_BASE(i.e 1000)


```kotlin
1000_000.bits.toKiloBytes(DECIMAL_BASE)            //  125.0 KB
1000_000.bits.toKiloBytes(BINARY_BASE)             //  122.0703125 KiB
```

**3) Output Prettify** : The Return type is  double meaning some conversions will go on for very long precisions. you can use the kotlin's `toLong()` function to convert to a whole number , or you can use the additionally provided `roundOff(precision:Int)` function to round off the number to a particular precision for better results

```kotlin
9999999.bits.toKiloBytes()              // 1249.99875 KB
9999999.bits.toKiloBytes().toLong()     // 1249 KB
9999999.bits.toKiloBytes().roundOff(2)  // 1250.0KB

9999999.bits.toMegaBytes()              // 1.24999875 MB
9999999.bits.toMegaBytes().toLong()     // 1 MB
9999999.bits.toMegaBytes().roundOff(2)  // 1.25 MB
```

**4) Automatically decide the unit** : If you are working with file systems, there are chances that you will not want to show user their large 5gb file as 5000 something mb .rather you would want to be flexible and convert the number to a pretty looking decimal number with an appropriate size unit. so we have another popular extension function `toMemoryString(..)` which will easily choose a unit accordingly

```kotlin
922337203685477580.toMemoryString(BINARY_BASE)          //819.2PiB
922337203685477580.toMemoryString(DECIMAL_BASE)         //922.3PB

922337203685477.toMemoryString()                        //922.3TB
92233720368.toMemoryString()                            //92.2GB
9223372.toMemoryString()                                //9.2MB



```
