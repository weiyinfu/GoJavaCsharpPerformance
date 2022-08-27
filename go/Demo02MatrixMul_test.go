package main

import (
	"fmt"
	"testing"
	"time"
)

func Test_matrixMul(t *testing.T) {
	begin := time.Now().UnixMilli()
	a := [1000][1000]int32{}
	b := [1000][1000]int32{}
	c := [1000][1000]int32{}
	for i := 0; i < 1000; i++ {
		for j := 0; j < 1000; j++ {
			for k := 0; k < 1000; k++ {
				c[i][j] += a[i][k] * b[k][j]
			}
		}
	}
	end := time.Now().UnixMilli()
	fmt.Println(end - begin)
}

func fib(x int64) int64 {
	if x <= 2 {
		return 1
	}
	return fib(x-1) + fib(x-2)
}
func Test_fibnaci(t *testing.T) {
	begin := time.Now().UnixMilli()
	fmt.Println(fib(45))
	end := time.Now().UnixMilli()
	fmt.Println(end - begin)
}
