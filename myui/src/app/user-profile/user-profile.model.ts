import {Address} from "./address.model";
import {SocialProfile} from "./social-profile.model";

export class UserProfile {

    public constructor(public imgPath: string
            , public name: string
            , public surname: string
            , public nickname: string
            , public address: Address
            , public email: string
            , public password: string
            , public socialProfile: SocialProfile) {}

}
