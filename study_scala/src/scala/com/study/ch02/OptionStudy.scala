package com.study.ch02

//sealed 表示只能在当前文件被使用
//option的两种情况：空(Nothing) 和 非空 (Some[+A])
sealed trait Option[+A]

case object None extends Option[Nothing]

case class Some[+A](get:A) extends Option[A]

object Option{
    def none:Option[Nothing] = None
    def some[A](value:A):Option[A] = Some(value)
}

