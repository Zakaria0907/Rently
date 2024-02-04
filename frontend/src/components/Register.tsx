import { useRef, useState, useEffect } from "react";
import {
    faCheck,
    faTimes,
    faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from "../api/axios";
import { Link } from "react-router-dom";
import tw from "tailwind-styled-components";
import RentlyLogo from '../assets/icons/rently-logo.svg?react';

const EMAIL_REGEX = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
const NAME_REGEX =
    /^([a-zA-Z0-9]+|[a-zA-Z0-9]+\s{1}[a-zA-Z0-9]{1,}|[a-zA-Z0-9]+\s{1}[a-zA-Z0-9]{3,}\s{1}[a-zA-Z0-9]{1,})$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = "/auth/register";

const Register = (): JSX.Element => {
    const firstNameRef = useRef<HTMLInputElement>(null);
    const lastNameRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const errRef = useRef<HTMLParagraphElement>(null);

    const [firstName, setfirstName] = useState<string>("");
    const [validfirstName, SetValidfirstName] = useState<boolean>(false);
    const [firstNameFocus, setfirstNameFocus] = useState<boolean>(false);

    const [lastName, setlastName] = useState<string>("");
    const [validlastName, setValidlastName] = useState<boolean>(false);
    const [lastNameFocus, setlastNameFocus] = useState<boolean>(false);

    const [email, setEmail] = useState<string>("");
    const [validEmail, setValidEmail] = useState<boolean>(false);
    const [emailFocus, setEmailFocus] = useState<boolean>(false);

    const [pwd, setPwd] = useState<string>("");
    const [validPwd, setValidPwd] = useState<boolean>(false);
    const [pwdFocus, setPwdFocus] = useState<boolean>(false);

    const [matchPwd, setMatchPwd] = useState<string>("");
    const [validMatch, setValidMatch] = useState<boolean>(false);
    const [matchFocus, setMatchFocus] = useState<boolean>(false);

    const [errMsg, setErrMsg] = useState<string>("");
    const [success, setSuccess] = useState<boolean>(false);

    useEffect(() => {
        if (firstNameRef.current) {
            firstNameRef.current.focus();
        }
    }, []);

    useEffect(() => {
        const result = NAME_REGEX.test(firstName);
        console.log(result);
        console.log(firstName);
        SetValidfirstName(result);
    }, [firstName]);

    useEffect(() => {
        const result = NAME_REGEX.test(lastName);
        console.log(result);
        console.log(lastName);
        setValidlastName(result);
    }, [lastName]);

    useEffect(() => {
        const result = EMAIL_REGEX.test(email);
        console.log(result);
        console.log(email);
        setValidEmail(result);
    }, [email]);

    useEffect(() => {
        const result = PWD_REGEX.test(pwd);
        console.log(result);
        console.log(pwd);
        setValidPwd(result);
        const match = pwd === matchPwd;
        setValidMatch(match);
    }, [pwd, matchPwd]);

    useEffect(() => {
        setErrMsg("");
    }, [firstName, lastName, email, pwd, matchPwd]);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>): Promise<void> => {
        e.preventDefault();
        const confirmFirstName = NAME_REGEX.test(firstName);
        const confirmLastName = NAME_REGEX.test(lastName);
        const confirmEmail = EMAIL_REGEX.test(email);
        const confirmPwd = PWD_REGEX.test(pwd);
        if (!confirmFirstName || !confirmLastName || !confirmEmail || !confirmPwd) {
            setErrMsg("Invalid Entry/Entries");
            return;
        }
        try {
            const response: any = await axios.post(
                REGISTER_URL,
                JSON.stringify({ firstname: firstName, lastname: lastName, email: email, password: pwd, role: "USER" }),
                {
                    headers: { "Content-Type": "application/json" },
                    withCredentials: true,
                }
            );
            console.log(response?.data);
            console.log(response?.access_token);
            console.log(JSON.stringify(response));
            setSuccess(true);
            //clear state and controlled inputs
            //need value attrib on inputs for this
            setfirstName("");
            setPwd("");
            setMatchPwd("");
        } catch (err: any) {
            if (!err?.response) {
                setErrMsg("No Server Response");
            } else if (err.response?.status === 409) {
                setErrMsg("firstName Taken");
            } else {
                setErrMsg("Registration Failed");
            }
            if (errRef.current) {
                errRef.current.focus();
            }
        }
    };

    return (
        <>
            {success ? (
                <section>
                    <h1>Success!</h1>
                    <p>
                        <Link to="/login">Sign in</Link>
                    </p>
                </section>
            ) : (
                <MainContainer>
                    <Container>
                        <LogoContainer>
                            <RentlyLogo className="w-auto h-12" />
                        </LogoContainer>

                        <div>
                            {errMsg && <p ref={errRef} className="text-red-500">{errMsg}</p>}
                        </div>

                        <Form onSubmit={handleSubmit}>
                            <FormContainer>
                                <Label htmlFor="firstName">First name
                                    <FontAwesomeIcon
                                        icon={faCheck}
                                        className={`ml-2  ${validfirstName ? "text-green-500" : "hidden"}`}
                                    />
                                    <FontAwesomeIcon
                                        icon={faTimes}
                                        className={`ml-2  ${validfirstName || !firstName ? "hidden" : "text-red-500"}`}
                                    />
                                </Label>
                                <Input
                                    type="text"
                                    id="firstName" /* firstName -> lastName*/
                                    ref={firstNameRef}
                                    autoComplete="off"
                                    onChange={(e) => setfirstName(e.target.value)}
                                    value={firstName}
                                    required
                                    aria-invalid={validfirstName ? "false" : "true"}
                                    aria-describedby="uidnote"
                                    onFocus={() => setfirstNameFocus(true)}
                                    onBlur={() => setfirstNameFocus(false)} />

                                <p
                                    id="cnidnote" /*uidnote -> cnidnote*/
                                    className={
                                        `text-gray-500 ${firstNameFocus && firstName && !validfirstName ? "block" : "hidden"}`
                                    }
                                >
                                    <FontAwesomeIcon icon={faInfoCircle} className="mr-1" />
                                    <span className="whitespace-pre-line">
                                        4 to 24 characters.
                                        <br />
                                        Must begin with a letter.
                                        <br />
                                        Letters, numbers, underscores, hyphens allowed.
                                    </span>
                                </p>
                            </FormContainer>

                            <FormContainer>
                                <Label htmlFor="lastName">
                                    Last Name
                                    <FontAwesomeIcon
                                        icon={faCheck}
                                        className={`ml-2  ${validlastName ? "text-green-500" : "hidden"}`}
                                    />
                                    <FontAwesomeIcon
                                        icon={faTimes}
                                        className={`ml-2  ${validlastName || !lastName ? "hidden" : "text-red-500"}`}
                                    />
                                </Label>
                                <Input
                                    type="text"
                                    id="lastName"
                                    ref={lastNameRef}
                                    autoComplete="off"
                                    onChange={(e) => setlastName(e.target.value)}
                                    value={lastName}
                                    required
                                    aria-invalid={validlastName ? "false" : "true"}
                                    aria-describedby="cnidnote"
                                    onFocus={() => setlastNameFocus(true)}
                                    onBlur={() => setlastNameFocus(false)}
                                    className="mb-2"
                                />

                                <p
                                    id="cnidnote"
                                    className={`mb-2 ${lastNameFocus && lastName && !validlastName ? "block" : "hidden"}`}
                                >
                                    <FontAwesomeIcon icon={faInfoCircle} className="mr-2" />
                                    4 to 24 characters.
                                    <br />
                                    Must begin with a letter.
                                    <br />
                                    Letters, numbers, underscores, hyphens allowed.
                                </p>
                            </FormContainer>

                            <FormContainer>
                                <Label htmlFor="email">
                                    Email
                                    <FontAwesomeIcon
                                        icon={faCheck}
                                        className={`ml-2 ${validEmail ? "text-green-500" : "hidden"}`}
                                    />
                                    <FontAwesomeIcon
                                        icon={faTimes}
                                        className={`ml-2 ${validEmail || !email ? "hidden" : "text-red-500"}`}
                                    />
                                </Label>
                                <Input
                                    type="text"
                                    id="email"
                                    ref={emailRef}
                                    autoComplete="off"
                                    onChange={(e) => setEmail(e.target.value)}
                                    value={email}
                                    required
                                    aria-invalid={validEmail ? "false" : "true"}
                                    aria-describedby="emailnote"
                                    onFocus={() => setEmailFocus(true)}
                                    onBlur={() => setEmailFocus(false)}
                                    className="mb-2"
                                />

                                <p
                                    id="emailnote"
                                    className={`mb-2 ${emailFocus && email && !validEmail ? "block" : "hidden"}`}
                                >
                                    <FontAwesomeIcon icon={faInfoCircle} className="mr-2" />
                                    Please enter a valid email address.
                                </p>
                            </FormContainer>

                            <FormContainer>
                                <div className="flex items-center justify-between">
                                    <PasswordLabel htmlFor="password">
                                        Password
                                        <FontAwesomeIcon
                                            icon={faCheck}
                                            className={`ml-2 ${validPwd ? "text-green-500" : "hidden"}`}
                                        />
                                        <FontAwesomeIcon
                                            icon={faTimes}
                                            className={`ml-2 ${validPwd || !pwd ? "hidden" : "text-red-500"}`}
                                        />
                                    </PasswordLabel>
                                </div>

                                <PasswordInput
                                    type="password"
                                    id="password"
                                    onChange={(e) => setPwd(e.target.value)}
                                    value={pwd}
                                    required
                                    aria-invalid={validPwd ? "false" : "true"}
                                    aria-describedby="pwdnote"
                                    onFocus={() => setPwdFocus(true)}
                                    onBlur={() => setPwdFocus(false)}
                                    className="mb-2"
                                />

                                <p
                                    id="pwdnote"
                                    className={`mb-2 ${pwdFocus && !validPwd ? "block" : "hidden"}`}
                                >
                                    <FontAwesomeIcon icon={faInfoCircle} className="mr-2" />
                                    8 to 24 characters.
                                    <br />
                                    Must include uppercase and lowercase letters, a number and a special character.
                                    <br />
                                    Allowed special characters: ! @ # $ %
                                </p>
                            </FormContainer>

                            <FormContainer>
                                <div className="flex items-center justify-between">
                                    <PasswordLabel htmlFor="confirm_pwd" >
                                        Confirm Password
                                        <FontAwesomeIcon
                                            icon={faCheck}
                                            className={`ml-2 ${validMatch && matchPwd ? "text-green-500" : "hidden"}`}
                                        />
                                        <FontAwesomeIcon
                                            icon={faTimes}
                                            className={`ml-2 ${validMatch || !matchPwd ? "hidden" : "text-red-500"}`}
                                        />
                                    </PasswordLabel>
                                </div>

                                <PasswordInput
                                    type="password"
                                    id="confirm_pwd"
                                    onChange={(e) => setMatchPwd(e.target.value)}
                                    value={matchPwd}
                                    required
                                    aria-invalid={validMatch ? "false" : "true"}
                                    aria-describedby="confirmnote"
                                    onFocus={() => setMatchFocus(true)}
                                    onBlur={() => setMatchFocus(false)}
                                    className="mb-2"
                                />
                                <p
                                    id="confirmnote"
                                    className={`mb-2 ${matchFocus && !validMatch ? "block" : "hidden"}`}
                                >
                                    <FontAwesomeIcon icon={faInfoCircle} className="mr-2" />
                                    Must match the first password input field.
                                </p>
                            </FormContainer>

                            <div className="mt-6">
                                <Button>Sign In</Button>
                            </div>
                        </Form>
                        <AccountMessage>
                            Already have an account? <AccountLink to="/login">Login</AccountLink>
                        </AccountMessage>
                    </Container>
                </MainContainer>
            )}
        </>
    );

    return (
        <>
            {success ? (
                <section>
                    <h1>Success!</h1>
                    <p>
                        <Link to="/login">Sign in</Link>
                    </p>
                </section>
            ) : (
                <>
                    <p
                        ref={errRef}
                        className={errMsg ? "errmsg" : "offscreen"}
                        aria-live="assertive"
                    >
                        {errMsg}
                    </p>
                    <h1>Register User Account</h1>

                    <form onSubmit={handleSubmit}>
                        <label htmlFor="firstName">
                            Full Name:
                            <FontAwesomeIcon
                                icon={faCheck}
                                className={validfirstName ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                                icon={faTimes}
                                className={validfirstName || !firstName ? "hide" : "invalid"}
                            />
                        </label>
                        <input
                            type="text"
                            id="firstName" /* firstName -> lastName*/
                            ref={firstNameRef}
                            autoComplete="off"
                            onChange={(e) => setfirstName(e.target.value)}
                            value={firstName}
                            required
                            aria-invalid={validfirstName ? "false" : "true"}
                            aria-describedby="uidnote"
                            onFocus={() => setfirstNameFocus(true)}
                            onBlur={() => setfirstNameFocus(false)}
                        />
                        <p
                            id="cnidnote" /*uidnote -> cnidnote*/
                            className={
                                firstNameFocus && firstName && !validfirstName
                                    ? "instructions"
                                    : "offscreen"
                            }
                        >
                            <FontAwesomeIcon icon={faInfoCircle} />
                            4 to 24 characters.
                            <br />
                            Must begin with a letter.
                            <br />
                            Letters, numbers, underscores, hyphens allowed.
                        </p>

                        <label htmlFor="companyemail">
                            Company Name:
                            <FontAwesomeIcon
                                icon={faCheck}
                                className={validlastName ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                                icon={faTimes}
                                className={
                                    validlastName || !lastName ? "hide" : "invalid"
                                }
                            />
                        </label>
                        <input
                            type="text"
                            id="companyemail" /* firstName -> companyemail*/
                            ref={firstNameRef}
                            autoComplete="off"
                            onChange={(e) => setlastName(e.target.value)}
                            value={lastName}
                            required
                            aria-invalid={validlastName ? "false" : "true"}
                            aria-describedby="uidnote"
                            onFocus={() => setlastNameFocus(true)}
                            onBlur={() => setlastNameFocus(false)}
                        />
                        <p
                            id="emailnote" /*uidnote -> emailnote*/
                            className={
                                lastNameFocus && lastName && !validlastName
                                    ? "instructions"
                                    : "offscreen"
                            }
                        >
                            <FontAwesomeIcon icon={faInfoCircle} />
                            4 to 24 characters.
                            <br />
                            Must begin with a letter.
                            <br />
                            Letters, numbers, underscores, hyphens allowed.
                        </p>

                        <label htmlFor="password">
                            Password:
                            <FontAwesomeIcon
                                icon={faCheck}
                                className={validPwd ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                                icon={faTimes}
                                className={validPwd || !pwd ? "hide" : "invalid"}
                            />
                        </label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                            aria-invalid={validPwd ? "false" : "true"}
                            aria-describedby="pwdnote"
                            onFocus={() => setPwdFocus(true)}
                            onBlur={() => setPwdFocus(false)}
                        />
                        <p
                            id="pwdnote"
                            className={pwdFocus && !validPwd ? "instructions" : "offscreen"}
                        >
                            <FontAwesomeIcon icon={faInfoCircle} />
                            8 to 24 characters.
                            <br />
                            Must include uppercase and lowercase letters, a number and a
                            special character.
                            <br />
                            Allowed special characters:{" "}
                            <span aria-label="exclamation mark">!</span>{" "}
                            <span aria-label="at symbol">@</span>{" "}
                            <span aria-label="hashtag">#</span>{" "}
                            <span aria-label="dollar sign">$</span>{" "}
                            <span aria-label="percent">%</span>
                        </p>

                        <label htmlFor="confirm_pwd">
                            Confirm Password:
                            <FontAwesomeIcon
                                icon={faCheck}
                                className={validMatch && matchPwd ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                                icon={faTimes}
                                className={validMatch || !matchPwd ? "hide" : "invalid"}
                            />
                        </label>
                        <input
                            type="password"
                            id="confirm_pwd"
                            onChange={(e) => setMatchPwd(e.target.value)}
                            value={matchPwd}
                            required
                            aria-invalid={validMatch ? "false" : "true"}
                            aria-describedby="confirmnote"
                            onFocus={() => setMatchFocus(true)}
                            onBlur={() => setMatchFocus(false)}
                        />
                        <p
                            id="confirmnote"
                            className={
                                matchFocus && !validMatch ? "instructions" : "offscreen"
                            }
                        >
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Must match the first password input field.
                        </p>

                        <button
                            disabled={
                                !validfirstName || !validlastName || !validPwd || !validMatch
                            }
                        >
                            Sign Up
                        </button>
                    </form>
                    <p>
                        Already registered?
                        <br />
                        <span className="line">
                            <Link to="/login">Sign in</Link>
                        </span>
                    </p>
                </>
            )}
        </>
    );
};

export default Register;

const MainContainer = tw.div`h-screen flex items-center justify-center bg-gray-100`;

const FormContainer = tw.div`mt-4`

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