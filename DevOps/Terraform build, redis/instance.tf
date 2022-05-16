resource "aws_instance" "project-instance" {
  ami           = var.AMIS[var.AWS_REGION]
  instance_type = "t2.micro"

  subnet_id = aws_subnet.main-public-subnet1.id

  vpc_security_group_ids = [aws_security_group.sg-instance.id]

  key_name = aws_key_pair.keypair.key_name

  iam_instance_profile = aws_iam_instance_profile.project-ec2-profile.name
}