package com.jorfald.apitest

class X(
    computer: A
)

// Computer
class A(
    val hardware: B,
    val installedSoftware: List<C>
)

// Hardware
class B(
    val ramSizeInGB: Int,
    val harddisk: D
)

// Software
class C(
    val name: String,
    val size: Double
)

// Harddisk
class D(
    val brand: String,
    val sizeInGB: Int,
    val type: String
)