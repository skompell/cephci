# -*- coding: utf-8 -*-
try:
    from setuptools import find_packages, setup
except ImportError:
    from ez_setup import use_setuptools

    use_setuptools()
    from setuptools import find_packages, setup

setup(
    name="cephci",
    version="0.1",
    description="Ceph CI tests that run in jenkins using openstack provider",
    author="Vasu Kulkarni",
    author_email="vasu@redhat.com",
    install_requires=[
        "apache-libcloud>=3.3.0",
        "docopt==0.6.2",
        "gevent==1.4.0 ; python_version<'3.7'",
        "gevent ; python_version>='3.7'",
        "greenlet==0.4.16 ; python_version<'3.7'",
        "greenlet ; python_version>='3.7'",
        "reportportal-client",
        "requests",
        "paramiko==2.10.1 ; python_version<'3.10'",
        "paramiko ; python_version>='3.10'",
        "pyOpenSSL==20.0.1 ; python_version<'3.10'",
        "pyOpenSSL ; python_version>='3.10'",
        "pyyaml>=4.2b1",
        "jinja2<3.1.0",
        "junitparser==1.4.0",
        "jinja_markdown",
        "htmllistparse==0.5.2",
        "ibm-cloud-sdk-core==3.15.0 ; python_version<'3.7'",
        "ibm-cloud-sdk-core ; python_version>='3.7'",
        "ibm-cos-sdk",
        "ibm-cos-sdk-core",
        "ibm-cos-sdk-s3transfer",
        "ibm-vpc>=0.8.0",
        "ibm-cloud-networking-services",
        "softlayer",
        "cryptography==36.0.1 ; python_version<'3.10'",
        "cryptography ; python_version>='3.10'",
    ],
    zip_safe=True,
    include_package_data=True,
    packages=find_packages(exclude=["ez_setup"]),
)
