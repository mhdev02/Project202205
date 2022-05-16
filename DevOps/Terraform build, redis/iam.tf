resource "aws_iam_role" "project-ec2-role" {
  name               = "project-ec2-role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF

}


resource "aws_iam_instance_profile" "project-ec2-profile" {
  name = "project-ec2-profile"
  role = aws_iam_role.project-ec2-role.name
}