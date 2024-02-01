import { useRef, useState, useEffect } from "react";
import {
    faCheck,
    faTimes,
    faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from "../api/axios";
import { Link } from "react-router-dom";

const NAME_REGEX =
    /^([a-zA-Z0-9]+|[a-zA-Z0-9]+\s{1}[a-zA-Z0-9]{1,}|[a-zA-Z0-9]+\s{1}[a-zA-Z0-9]{3,}\s{1}[a-zA-Z0-9]{1,})$/;
const COMPANY_REGEX = /^([a-zA-Z0-9]+|[a-zA-Z0-9]+\s{1}[a-zA-Z0-9]{1,}|[a-zA-Z0-9]+\s{1}[a-zA-Z0-9]{3,}\s{1}[a-zA-Z0-9]{1,})$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = "/register";

const RegisterUser = (): JSX.Element => {
    const userNameRef = useRef<HTMLInputElement>(null);
    const errRef = useRef<HTMLParagraphElement>(null);

    const [userName, setUserName] = useState<string>("");
    const [validUserName, SetValidUserName] = useState<boolean>(false);
    const [userNameFocus, setUserNameFocus] = useState<boolean>(false);

    const [companyName, setCompanyName] = useState<string>("");
    const [validCompanyName, setValidCompanyName] = useState<boolean>(false);
    const [companyNameFocus, setCompanyNameFocus] = useState<boolean>(false);

    const [pwd, setPwd] = useState<string>("");
    const [validPwd, setValidPwd] = useState<boolean>(false);
    const [pwdFocus, setPwdFocus] = useState<boolean>(false);

    const [matchPwd, setMatchPwd] = useState<string>("");
    const [validMatch, setValidMatch] = useState<boolean>(false);
    const [matchFocus, setMatchFocus] = useState<boolean>(false);

    const [errMsg, setErrMsg] = useState<string>("");
    const [success, setSuccess] = useState<boolean>(false);

    useEffect(() => {
        if (userNameRef.current) {
            userNameRef.current.focus();
        }
    }, []);

    useEffect(() => {
        const result = NAME_REGEX.test(userName);
        console.log(result);
        console.log(userName);
        SetValidUserName(result);
    }, [userName]);

    useEffect(() => {
        const result = COMPANY_REGEX.test(companyName);
        console.log(result);
        console.log(companyName);
        setValidCompanyName(result);
    }, [companyName]);

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
    }, [userName, companyName, pwd, matchPwd]);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>): Promise<void> => {
        e.preventDefault();
        const confirmCompanyName = NAME_REGEX.test(userName);
        const confirmEmail = COMPANY_REGEX.test(companyName);
        const confirmPwd = PWD_REGEX.test(pwd);
        if (!confirmCompanyName || !confirmEmail || !confirmPwd) {
            setErrMsg("Invalid Entry/Entries");
            return;
        }
        try {
            const response: any = await axios.post(
                REGISTER_URL,
                JSON.stringify({ user: userName, company: companyName, pwd: pwd }),
                {
                    headers: { "Content-Type": "application/json" },
                    withCredentials: true,
                }
            );
            console.log(response?.data);
            console.log(response?.accessToken);
            console.log(JSON.stringify(response));
            setSuccess(true);
            //clear state and controlled inputs
            //need value attrib on inputs for this
            setUserName("");
            setPwd("");
            setMatchPwd("");
        } catch (err : any) {
            if (!err?.response) {
                setErrMsg("No Server Response");
            } else if (err.response?.status === 409) {
                setErrMsg("Username Taken");
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
                <section>
                    <p
                        ref={errRef}
                        className={errMsg ? "errmsg" : "offscreen"}
                        aria-live="assertive"
                    >
                        {errMsg}
                    </p>
                    <h1>Register User Account</h1>

                    <form onSubmit={handleSubmit}>
                        <label htmlFor="username">
                            Full Name:
                            <FontAwesomeIcon
                                icon={faCheck}
                                className={validUserName ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                                icon={faTimes}
                                className={validUserName || !userName ? "hide" : "invalid"}
                            />
                        </label>
                        <input
                            type="text"
                            id="username" /* username -> companyname*/
                            ref={userNameRef}
                            autoComplete="off"
                            onChange={(e) => setUserName(e.target.value)}
                            value={userName}
                            required
                            aria-invalid={validUserName ? "false" : "true"}
                            aria-describedby="uidnote"
                            onFocus={() => setUserNameFocus(true)}
                            onBlur={() => setUserNameFocus(false)}
                        />
                        <p
                            id="cnidnote" /*uidnote -> cnidnote*/
                            className={
                                userNameFocus && userName && !validUserName
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
                                className={validCompanyName ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                                icon={faTimes}
                                className={
                                    validCompanyName || !companyName ? "hide" : "invalid"
                                }
                            />
                        </label>
                        <input
                            type="text"
                            id="companyemail" /* username -> companyemail*/
                            ref={userNameRef}
                            autoComplete="off"
                            onChange={(e) => setCompanyName(e.target.value)}
                            value={companyName}
                            required
                            aria-invalid={validCompanyName ? "false" : "true"}
                            aria-describedby="uidnote"
                            onFocus={() => setCompanyNameFocus(true)}
                            onBlur={() => setCompanyNameFocus(false)}
                        />
                        <p
                            id="emailnote" /*uidnote -> emailnote*/
                            className={
                                companyNameFocus && companyName && !validCompanyName
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
                                !validUserName || !validCompanyName || !validPwd || !validMatch
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
                </section>
            )}
        </>
    );
};

export default RegisterUser;