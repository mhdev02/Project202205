variable "AWS_REGION" {
  default = "ap-northeast-2"
}

variable "PRIVATE_KEY" {
  default = "key"
}

variable "PUBLIC_KEY" {
  default = "key.pub"
}


variable "AMIS" {
  type = map(string)
  default = {
    ap-northeast-2 = "ami-0cbec04a61be382d9"
  }
}