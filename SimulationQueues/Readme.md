{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf830
{\fonttbl\f0\fnil\fcharset0 Menlo-Regular;}
{\colortbl;\red255\green255\blue255;\red0\green116\blue0;\red0\green0\blue0;}
{\*\expandedcolortbl;;\csgenericrgb\c0\c45600\c0;\csgenericrgb\c0\c0\c0;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab543
\pard\tx543\pardeftab543\pardirnatural\partightenfactor0

\f0\fs22 \cf2 \CocoaLigature0 //--------------------------------------------------------------------------\cf3 \
\cf2 //\cf3 \
\cf2 // Define simulation queues in a checkout area. Queues hold references to Customer \cf3 \
\cf2 // and Cashier objects\cf3 \
\cf2 //\cf3 \
\cf2 // Customer (FIFO) queue is used to hold waiting customers. If the queue is too long\cf3 \
\cf2 // (i.e. >  customerQLimit), customer goes away without entering customer queue\cf3 \
\cf2 //\cf3 \
\cf2 // There are several cashiers in a checkout area. Use PriorityQueue to \cf3 \
\cf2 // hold BUSY cashiers and FIFO queue to hold FREE cashiers, \cf3 \
\cf2 // i.e. a cashier that is FREE for the longest time should start be used first.\cf3 \
\cf2 //\cf3 \
\cf2 // To handle cashier in PriorityQueue, we need to define comparator \cf3 \
\cf2 // for comparing 2 cashier objects. Here is a constructor from Java API:\cf3 \
\cf2 //\cf3 \
\cf2 // 	PriorityQueue(int initialCapacity, Comparator<? super E> comparator) \cf3 \
\cf2 //\cf3 \
\cf2 // For priority queue, the default compare function is "natural ordering"\cf3 \
\cf2 // i.e. for numbers, minimum value is returned first\cf3 \
\cf2 //\cf3 \
\cf2 // User can define own comparator class for PriorityQueue.\cf3 \
\cf2 // For cashier objects, we like to have smallest end busy interval time first.\cf3 \
\cf2 // i.e. use Cashier's getEndBusyTime() \cf3 \
\cf2 //\cf3 \
\cf2 // The following class define compare() for two cashiers :}