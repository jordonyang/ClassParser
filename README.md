## Introduction

A Java class file parser based on Java Visual Machine Specification，just like `javap` which is an inclusive powerful tool of Java Developer's Kit to parser byte code. 

## Simple Test

source file of the test case 

```java
package decompile;

import java.util.Iterator;

public class IterableClass implements Iterable<String>{

    private String[] arr;

    public IterableClass(String[] arr) {
        this.arr = arr;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() { 
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < arr.length;
            }

            @Override
            public String next() {
                return arr[index++];
            }

            @Override
            public void remove() {
               //TODO
            }
        };
    }
}
```

hexadecimal data of the class file

```
CA, FE, BA, BE, 00, 00, 00, 34, 00, 25, 09, 00, 05, 00, 1C, 0A
00, 06, 00, 1D, 07, 00, 1E, 0A, 00, 03, 00, 1F, 07, 00, 20, 07
00, 21, 07, 00, 22, 01, 00, 0C, 49, 6E, 6E, 65, 72, 43, 6C, 61
73, 73, 65, 73, 01, 00, 03, 61, 72, 72, 01, 00, 13, 5B, 4C, 6A
61, 76, 61, 2F, 6C, 61, 6E, 67, 2F, 53, 74, 72, 69, 6E, 67, 3B
01, 00, 06, 3C, 69, 6E, 69, 74, 3E, 01, 00, 16, 28, 5B, 4C, 6A
61, 76, 61, 2F, 6C, 61, 6E, 67, 2F, 53, 74, 72, 69, 6E, 67, 3B
29, 56, 01, 00, 04, 43, 6F, 64, 65, 01, 00, 0F, 4C, 69, 6E, 65
4E, 75, 6D, 62, 65, 72, 54, 61, 62, 6C, 65, 01, 00, 12, 4C, 6F
63, 61, 6C, 56, 61, 72, 69, 61, 62, 6C, 65, 54, 61, 62, 6C, 65
01, 00, 04, 74, 68, 69, 73, 01, 00, 19, 4C, 64, 65, 63, 6F, 6D
70, 69, 6C, 65, 2F, 49, 74, 65, 72, 61, 62, 6C, 65, 43, 6C, 61
73, 73, 3B, 01, 00, 08, 69, 74, 65, 72, 61, 74, 6F, 72, 01, 00
16, 28, 29, 4C, 6A, 61, 76, 61, 2F, 75, 74, 69, 6C, 2F, 49, 74
65, 72, 61, 74, 6F, 72, 3B, 01, 00, 09, 53, 69, 67, 6E, 61, 74
75, 72, 65, 01, 00, 2A, 28, 29, 4C, 6A, 61, 76, 61, 2F, 75, 74
69, 6C, 2F, 49, 74, 65, 72, 61, 74, 6F, 72, 3C, 4C, 6A, 61, 76
61, 2F, 6C, 61, 6E, 67, 2F, 53, 74, 72, 69, 6E, 67, 3B, 3E, 3B
01, 00, 0A, 61, 63, 63, 65, 73, 73, 24, 30, 30, 30, 01, 00, 2E
28, 4C, 64, 65, 63, 6F, 6D, 70, 69, 6C, 65, 2F, 49, 74, 65, 72
61, 62, 6C, 65, 43, 6C, 61, 73, 73, 3B, 29, 5B, 4C, 6A, 61, 76
61, 2F, 6C, 61, 6E, 67, 2F, 53, 74, 72, 69, 6E, 67, 3B, 01, 00
02, 78, 30, 01, 00, 3A, 4C, 6A, 61, 76, 61, 2F, 6C, 61, 6E, 67
2F, 4F, 62, 6A, 65, 63, 74, 3B, 4C, 6A, 61, 76, 61, 2F, 6C, 61
6E, 67, 2F, 49, 74, 65, 72, 61, 62, 6C, 65, 3C, 4C, 6A, 61, 76
61, 2F, 6C, 61, 6E, 67, 2F, 53, 74, 72, 69, 6E, 67, 3B, 3E, 3B
01, 00, 0A, 53, 6F, 75, 72, 63, 65, 46, 69, 6C, 65, 01, 00, 12
49, 74, 65, 72, 61, 62, 6C, 65, 43, 6C, 61, 73, 73, 2E, 6A, 61
76, 61, 0C, 00, 09, 00, 0A, 0C, 00, 0B, 00, 23, 01, 00, 19, 64
65, 63, 6F, 6D, 70, 69, 6C, 65, 2F, 49, 74, 65, 72, 61, 62, 6C
65, 43, 6C, 61, 73, 73, 24, 31, 0C, 00, 0B, 00, 24, 01, 00, 17
64, 65, 63, 6F, 6D, 70, 69, 6C, 65, 2F, 49, 74, 65, 72, 61, 62
6C, 65, 43, 6C, 61, 73, 73, 01, 00, 10, 6A, 61, 76, 61, 2F, 6C
61, 6E, 67, 2F, 4F, 62, 6A, 65, 63, 74, 01, 00, 12, 6A, 61, 76
61, 2F, 6C, 61, 6E, 67, 2F, 49, 74, 65, 72, 61, 62, 6C, 65, 01
00, 03, 28, 29, 56, 01, 00, 1C, 28, 4C, 64, 65, 63, 6F, 6D, 70
69, 6C, 65, 2F, 49, 74, 65, 72, 61, 62, 6C, 65, 43, 6C, 61, 73
73, 3B, 29, 56, 00, 21, 00, 05, 00, 06, 00, 01, 00, 07, 00, 01
00, 02, 00, 09, 00, 0A, 00, 00, 00, 03, 00, 01, 00, 0B, 00, 0C
00, 01, 00, 0D, 00, 00, 00, 46, 00, 02, 00, 02, 00, 00, 00, 0A
2A, B7, 00, 02, 2A, 2B, B5, 00, 01, B1, 00, 00, 00, 02, 00, 0E
00, 00, 00, 0E, 00, 03, 00, 00, 00, 09, 00, 04, 00, 0A, 00, 09
00, 0B, 00, 0F, 00, 00, 00, 16, 00, 02, 00, 00, 00, 0A, 00, 10
00, 11, 00, 00, 00, 00, 00, 0A, 00, 09, 00, 0A, 00, 01, 00, 01
00, 12, 00, 13, 00, 02, 00, 0D, 00, 00, 00, 33, 00, 03, 00, 01
00, 00, 00, 09, BB, 00, 03, 59, 2A, B7, 00, 04, B0, 00, 00, 00
02, 00, 0E, 00, 00, 00, 06, 00, 01, 00, 00, 00, 0F, 00, 0F, 00
00, 00, 0C, 00, 01, 00, 00, 00, 09, 00, 10, 00, 11, 00, 00, 00
14, 00, 00, 00, 02, 00, 15, 10, 08, 00, 16, 00, 17, 00, 01, 00
0D, 00, 00, 00, 2F, 00, 01, 00, 01, 00, 00, 00, 05, 2A, B4, 00
01, B0, 00, 00, 00, 02, 00, 0E, 00, 00, 00, 06, 00, 01, 00, 00
00, 05, 00, 0F, 00, 00, 00, 0C, 00, 01, 00, 00, 00, 05, 00, 18
00, 11, 00, 00, 00, 03, 00, 14, 00, 00, 00, 02, 00, 19, 00, 1A
00, 00, 00, 02, 00, 1B, 00, 08, 00, 00, 00, 0A, 00, 01, 00, 03
00, 00, 00, 00, 00, 00
```

*analytical result*  of the constant pool

```
  #1 = Filedref        #5, #28   // decompile/IterableClass, arr:[Ljava/lang/String;
  #2 = Methodref       #6, #29   // java/lang/Object, <init>:()V
  #3 = Class           #30       // decompile/IterableClass$1
  #4 = Methodref       #3, #31   // decompile/IterableClass$1, <init>:(Ldecompile/IterableClass;)V
  #5 = Class           #32       // decompile/IterableClass
  #6 = Class           #33       // java/lang/Object
  #7 = Class           #34       // java/lang/Iterable
  #8 = Utf8            InnerClasses
  #9 = Utf8            arr       
  #10 = Utf8           [Ljava/lang/String;
  #11 = Utf8           <init>    
  #12 = Utf8           ([Ljava/lang/String;)V
  #13 = Utf8           Code      
  #14 = Utf8           LineNumberTable
  #15 = Utf8           LocalVariableTable
  #16 = Utf8           this      
  #17 = Utf8           Ldecompile/IterableClass;
  #18 = Utf8           iterator  
  #19 = Utf8           ()Ljava/util/Iterator;
  #20 = Utf8           Signature 
  #21 = Utf8           ()Ljava/util/Iterator<Ljava/lang/String;>;
  #22 = Utf8           access$000
  #23 = Utf8           (Ldecompile/IterableClass;)[Ljava/lang/String;
  #24 = Utf8           x0        
  #25 = Utf8           Ljava/lang/Object;Ljava/lang/Iterable<Ljava/lang/String;>;
  #26 = Utf8           SourceFile
  #27 = Utf8           IterableClass.java
  #28 = NameAndType    #9, #10   // arr:[Ljava/lang/String;
  #29 = NameAndType    #11, #35  // <init>:()V
  #30 = Utf8           decompile/IterableClass$1
  #31 = NameAndType    #11, #36  // <init>:(Ldecompile/IterableClass;)V
  #32 = Utf8           decompile/IterableClass
  #33 = Utf8           java/lang/Object
  #34 = Utf8           java/lang/Iterable
  #35 = Utf8           ()V       
  #36 = Utf8           (Ldecompile/IterableClass;)V
```



