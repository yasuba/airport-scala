package models

class Plane(var flying: Boolean = true) {

  def land = {
   flying = false
    flying
  }

  def fly = {
    flying = true
    flying
  }

}
