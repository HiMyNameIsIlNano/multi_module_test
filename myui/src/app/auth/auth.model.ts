export class Auth {

    private token: string;
    constructor(private user: string, public password: string) {}

    static withToken(user: string, token: string): Auth {
        const auth = new Auth(user, null);
        auth.token = token;
        return auth;
    }

    static fromLocalStorage(local: string): Auth {
        let username = null;
        let token = null;

        if (local != null){
            username = JSON.parse(local).username;
            token = JSON.parse(local).token;
        }

        const auth = new Auth(username, null);
        auth.token = token;
        return auth;
    }

    getUser(): string {
        return this.user;
    }

    getToken(): string {
        return this.token;
    }

}
