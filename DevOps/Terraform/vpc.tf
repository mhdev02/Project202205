resource "aws_vpc" "vpc-main" {
  cidr_block           = "10.0.0.0/16"
  instance_tenancy     = "default"
  enable_dns_support   = "true"
  enable_dns_hostnames = "true"
  enable_classiclink   = "false"
  tags = {
    Name = "main vpc"
  }
}


resource "aws_subnet" "main-public-subnet1" {
  vpc_id                  = aws_vpc.vpc-main.id
  cidr_block              = "10.0.1.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = "ap-northeast-2"

  tags = {
    Name = "main-public-1"
  }
}


resource "aws_internet_gateway" "main-gateway" {
  vpc_id = aws_vpc.vpc-main.id

  tags = {
    Name = "main gateway"
  }
}


resource "aws_route_table" "main-rt-public" {
  vpc_id = aws_vpc.vpc-main.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main-gateway.id
  }

  tags = {
    Name = "main-rt-public"
  }
}


resource "aws_route_table_association" "main-rta-public" {
  subnet_id      = aws_subnet.main-public-subnet1.id
  route_table_id = aws_route_table.main-rt-public.id
}