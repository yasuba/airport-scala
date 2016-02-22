package models

class Plane(var isFlying: Boolean = true) {

  def land = {
    isFlying = false
  }

  def fly = {
    isFlying = true
  }

}
