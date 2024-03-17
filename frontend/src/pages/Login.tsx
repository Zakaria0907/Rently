import { useRef, useState, useEffect, ChangeEvent, FormEvent } from "react";
import useAuth from "../hooks/useAuth";
import { Link, useNavigate, useLocation } from "react-router-dom";
import tw from "tailwind-styled-components";
import RentlyLogo from '../images/icon/rently-logo.svg?react';
import ApiManager from "../api/ApiManager";
import { User } from "../types/types";
import toast, { Toaster } from 'react-hot-toast';
import { position } from "@chakra-ui/react";

const Login = () => {
    const { setAuth, persist, setPersist } = useAuth();

    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || "/";

    const userRef = useRef<HTMLInputElement>(null);
    const errRef = useRef<HTMLParagraphElement>(null);

    const [userName, setUserName] = useState("");
    const [pwd, setPwd] = useState("");
    const [errMsg, setErrMsg] = useState("");

    useEffect(() => {
        if (userRef.current) {
            userRef.current.focus();
        }
    }, []);

    useEffect(() => {
        setErrMsg("");
    }, [userName, pwd]);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        try {
            const response: any = await ApiManager.login({ email: userName, password: pwd });
            console.log("marker: ", JSON.stringify(response?.data));
            const user: User = {
                id: response?.data?.user?.id,
                firstname: response?.data?.user?.firstname,
                lastname: response?.data?.user?.lastname,
                phoneNumber: response?.data?.user?.phoneNumber,
                bio: response?.data?.user?.bio,
                email: response?.data?.user?.email,
                roles: response?.data?.user?.role,
            }
            setAuth(user);
            setUserName("");
            setPwd("");
            // navigate(from, { replace: true }); // PUT IT BACK AFTER
            navigate("/login-success");
            toast.success("Login Successful");
        } catch (err: any) {
            if (!err?.response) {
                toast.error("Network Error");
            } else if (err.response?.status === 400) {
               toast.error("Invalid Credentials");
            } else if (err.response?.status === 401) {
                toast.error("Invalid Credentials");
            } else {
                toast.error("Unable to login");
            }
            if (errRef.current) {
                errRef.current.focus();
            }
        }
    };

    const togglePersist = () => {
        setPersist(!persist);
    };

    useEffect(() => {
        localStorage.setItem("persist", String(persist));
    }, [persist]);

    return (
        <MainContainer>
            <Container>
                <LogoContainer>
                    <RentlyLogo className="w-auto h-12" />
                </LogoContainer>
                
                <Toaster position="top-right"/>

                <Form onSubmit={handleSubmit}>
                    <div>
                        <Label htmlFor="username">Username</Label>
                        <Input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e: ChangeEvent<HTMLInputElement>) =>
                                setUserName(e.target.value)
                            }
                            value={userName}
                            required />
                    </div>

                    <PasswordContainer >
                        <div className="flex items-center justify-between">
                            <PasswordLabel htmlFor="password">Password</PasswordLabel>
                            <ForgetPasswordLink href="#">Forget Password?</ForgetPasswordLink>
                        </div>

                        <PasswordInput
                            type="password"
                            id="password"
                            onChange={(e: ChangeEvent<HTMLInputElement>) =>
                                setPwd(e.target.value)
                            }
                            value={pwd}
                            required />
                    </PasswordContainer>

                    <div className="mt-4 flex flex-row gap-2">
                        <input
                            type="checkbox"
                            id="persist"
                            onChange={togglePersist}
                            checked={persist}
                        />
                        <label className={'text-sm text-gray-600'} htmlFor="persist">Trust this device?</label>
                    </div>

                    <div className="mt-6">
                        <Button>Sign In</Button>
                    </div>
                </Form>

                <LoginSocialsContainer >
                    <Divider></Divider>

                    <SocialMediaLink href="#">
                        or login with Social Media
                    </SocialMediaLink>

                    <SocialMediaDivider></SocialMediaDivider>
                </LoginSocialsContainer>

                <SocialsContainer >
                    {/*TODO: Make appropriate href for social media login*/}
                    <a href="http://localhost:8080/oauth2/authorization/google" className="w-full">
                        <GoogleButton type="button">
                            <GoogleIcon viewBox="0 0 24 24">
                                <path d="M12.24 10.285V14.4h6.806c-.275 1.765-2.056 5.174-6.806 5.174-4.095 0-7.439-3.389-7.439-7.574s3.345-7.574 7.439-7.574c2.33 0 3.891.989 4.785 1.849l3.254-3.138C18.189 1.186 15.479 0 12.24 0c-6.635 0-12 5.365-12 12s5.365 12 12 12c6.926 0 11.52-4.869 11.52-11.726 0-.788-.085-1.39-.189-1.989H12.24z">
                                </path>
                            </GoogleIcon>

                            <GoogleText>Sign in with Google</GoogleText>
                        </GoogleButton>
                    </a>

                    <SocialMediaButton href="#">
                        <SocialMediaIcon viewBox="0 0 24 24">
                            <path d="M23.954 4.569c-.885.389-1.83.654-2.825.775 1.014-.611 1.794-1.574 2.163-2.723-.951.555-2.005.959-3.127 1.184-.896-.959-2.173-1.559-3.591-1.559-2.717 0-4.92 2.203-4.92 4.917 0 .39.045.765.127 1.124C7.691 8.094 4.066 6.13 1.64 3.161c-.427.722-.666 1.561-.666 2.475 0 1.71.87 3.213 2.188 4.096-.807-.026-1.566-.248-2.228-.616v.061c0 2.385 1.693 4.374 3.946 4.827-.413.111-.849.171-1.296.171-.314 0-.615-.03-.916-.086.631 1.953 2.445 3.377 4.604 3.417-1.68 1.319-3.809 2.105-6.102 2.105-.39 0-.779-.023-1.17-.067 2.189 1.394 4.768 2.209 7.557 2.209 9.054 0 13.999-7.496 13.999-13.986 0-.209 0-.42-.015-.63.961-.689 1.8-1.56 2.46-2.548l-.047-.02z">
                            </path>
                        </SocialMediaIcon>
                    </SocialMediaButton>
                </SocialsContainer>

                <AccountMessage>
                    Don't have an account? <AccountLink to="/register">Create One</AccountLink>
                </AccountMessage>
            </Container>
        </MainContainer>
    );
};

const MainContainer = tw.div`h-screen flex items-center justify-center bg-gray-100`;

const PasswordContainer = tw.div`mt-4`

const SocialsContainer = tw.div`flex items-center mt-6 -mx-2`

const LoginSocialsContainer = tw.div`flex items-center justify-between mt-4`

const Container = tw.div`
    w-full max-w-sm p-6 m-auto mx-auto bg-white rounded-lg shadow-md
`;

const LogoContainer = tw.div`
    flex justify-center mx-auto
`;

const Logo = tw.img`
    w-auto h-7 sm:h-8
`;

const Form = tw.form`
    mt-6
`;

const Label = tw.label`
    block text-sm text-gray-800
`;

const Input = tw.input`
    block w-full px-4 py-2 mt-2 text-gray-700 bg-white border rounded-lg focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40
`;

const PasswordLabel = tw.label`
    block text-sm text-gray-800
`;

const ForgetPasswordLink = tw.a`
    text-xs text-gray-600 hover:underline
`;

const PasswordInput = tw.input`
    block w-full px-4 py-2 mt-2 text-gray-700 bg-white border rounded-lg focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40
`;

const Button = tw.button`
    w-full px-6 py-2.5 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50
`;

const Divider = tw.span`
    w-1/5 border-b lg:w-1/5
`;

const SocialMediaLink = tw.a`
    text-xs text-center text-gray-500 uppercase hover:underline
`;

const SocialMediaDivider = tw.span`
    w-1/5 border-b lg:w-1/5
`;

const GoogleButton = tw.button`
    flex items-center justify-center w-full px-6 py-2 mx-2 text-sm font-medium text-white transition-colors duration-300 transform bg-blue-500 rounded-lg hover:bg-blue-400 focus:bg-blue-400 focus:outline-none
`;

const GoogleIcon = tw.svg`
    w-4 h-4 mx-2 fill-current
`;

const GoogleText = tw.span`
    hidden mx-2 sm:inline
`;

const SocialMediaButton = tw.a`
    p-2 mx-2 text-sm font-medium text-gray-500 transition-colors duration-300 transform bg-gray-300 rounded-lg hover:bg-gray-200
`;

const SocialMediaIcon = tw.svg`
    w-5 h-5 fill-current
`;

const AccountMessage = tw.p`
    mt-8 text-xs font-light text-center text-gray-400
`;

const AccountLink = tw(Link)`
    font-medium text-gray-700 hover:underline
`;

export default Login;