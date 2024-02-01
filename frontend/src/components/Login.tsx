import { useRef, useState, useEffect, ChangeEvent, FormEvent } from "react";
import useAuth from "../hooks/useAuth";
import { Link, useNavigate, useLocation } from "react-router-dom";

import axios from "../api/axios";
const LOGIN_URL = "/auth";

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
            const response = await axios.post(
                LOGIN_URL,
                JSON.stringify({ user: userName, pwd: pwd }),
                {
                    headers: { "Content-Type": "application/json" },
                    withCredentials: true,
                }
            );
            console.log("marker");
            console.log(JSON.stringify(response?.data));
            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;
            const company = response?.data?.companyname;
            setAuth({ user: userName, pwd, roles, accessToken, company });
            setUserName("");
            setPwd("");
            navigate(from, { replace: true });
        } catch (err: any) {
            if (!err?.response) {
                setErrMsg("No Server Response");
            } else if (err.response?.status === 400) {
                setErrMsg("Missing Username or Password");
            } else if (err.response?.status === 401) {
                setErrMsg("Unauthorized");
            } else {
                setErrMsg("Login Failed");
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
        <section>
            <p
                ref={errRef}
                className={errMsg ? "errmsg" : "offscreen"}
                aria-live="assertive"
            >
                {errMsg}
            </p>
            <h1>Sign In</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    id="username"
                    ref={userRef}
                    autoComplete="off"
                    onChange={(e: ChangeEvent<HTMLInputElement>) =>
                        setUserName(e.target.value)
                    }
                    value={userName}
                    required
                />

                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    onChange={(e: ChangeEvent<HTMLInputElement>) =>
                        setPwd(e.target.value)
                    }
                    value={pwd}
                    required
                />
                <button>Sign In</button>
                <div className="persistCheck">
                    <input
                        type="checkbox"
                        id="persist"
                        onChange={togglePersist}
                        checked={persist}
                    />
                    <label htmlFor="persist">Trust This Device</label>
                </div>
            </form>
            <p>
                Need an Account?
                <br />
                <span className="line">
                    {/*put router link here*/}
                    <Link to="/register">Sign Up</Link>
                </span>
            </p>
        </section>
    );
};

export default Login;