variable "AWS_REGION" {
  default = "ap-northeast-2"
}

variable "PRIVATE_KEY" {
  default = ""
}

variable "PUBLIC_KEY" {
  default = ""
}


variable "AMIS" {
  type = map(string)
  default = {
    ap-northeast-2 = "ami-0cbec04a61be382d9"
  }
}

variable "AWS_ACCESS_KEY" {
  default = ""
}

variable "AWS_SECRET_KEY" {
  default = ""
}